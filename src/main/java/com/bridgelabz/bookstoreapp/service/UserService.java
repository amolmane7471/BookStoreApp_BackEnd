package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.LoginUser;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import com.bridgelabz.bookstoreapp.util.OtpGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private OtpGenerator otpGenerator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailSenderService mailService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User userDataByEmail = userRepository.findByemail(userDTO.getEmail());
        generateOtpAndSendEmail(userDataByEmail);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User userById = getUserById(id);
        System.out.println(userDTO.getPassword());
        modelMapper.map(userDTO, userById);
        System.out.println("user password after update completed=====>"+ userById.getPassword());
        userRepository.save(userById);
        return userById;
    }

    @Override
    public User deleteUser(Long id) {
        User userById = getUserById(id);
        userRepository.delete(userById);
        return userById;
    }

    @Override
    public String forgotPasswordRequest(String email) {
        User userData = userRepository.findByemail(email);
        if (userData == null) {
            return "user not found";
        }
        long otp = otpGenerator.generateOTP();
        String generatedOtp = "Otp is generated \n" + otp;
        mailService.sendEmailToUser(userData.getEmail(), "Your otp is ", generatedOtp);
        return "Reset Password otp Has Been Sent To Your Email Address " + userData.getEmail();
    }

    @Override
    public String resetPassword(LoginUser loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByemail(loginDTO.getEmail()));
        String password = loginDTO.getPassword();
        if (userDetails.isPresent()) {
            userDetails.get().setPassword(password);
            mailService.sendEmailToUser(userDetails.get().getEmail(), "Password Changed Successfully", userDetails.toString());
            return userDetails.toString();
        }
        return null;
    }


    private Long generateOtpAndSendEmail(User userData) {
        long generatedOtp = otpGenerator.generateOTP();
        String requestUrl = "http://localhost:9999/bookstore/verify/email/" + generatedOtp;
        System.out.println("the generated otp is " + generatedOtp);
        try {
            mailService.sendEmailToUser(
                    userData.getEmail(),
                    "Your Registration is successful",
                    requestUrl + "\n your generated otp is "
                            + generatedOtp +
                            " click on the link above to verify the user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedOtp;
    }


}
