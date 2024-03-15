package nl.bitsentools.eindprojectbackendmetabo.services;


import nl.bitsentools.eindprojectbackendmetabo.dto.user.UserInputDto;
import nl.bitsentools.eindprojectbackendmetabo.dto.user.UserOutputDto;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.RecordNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.exceptions.UsernameNotFoundException;
import nl.bitsentools.eindprojectbackendmetabo.models.Authority;
import nl.bitsentools.eindprojectbackendmetabo.models.UserModel;
import nl.bitsentools.eindprojectbackendmetabo.repositories.UserRepository;
import nl.bitsentools.eindprojectbackendmetabo.utils.RandomStringGenerator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserOutputDto> getAllUsers() {

        List<UserOutputDto>collection = new ArrayList<>();
        List<UserModel> userList = userRepository.findAll();
        for (UserModel user : userList) {
            collection.add(fromUser(user));
        }
        return collection;
    }
    public UserOutputDto getOneUser(String username) {
        UserOutputDto userOutputDto;
        Optional<UserModel> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            userOutputDto = fromUser(user.get());
        }
        return userOutputDto;
    }



    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserInputDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        UserModel newUser = userRepository.save(toUser(userDto));
        return newUser.getUserName();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserInputDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        UserModel user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        UserModel user = userRepository.findById(username).get();
        UserOutputDto userDto = fromUser(user);
        return userDto.getAuthority();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        UserModel user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        UserModel user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserOutputDto fromUser(UserModel user){

        var dto = new UserOutputDto();

        dto.username = user.getUserName();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApiKey();
        dto.email = user.getEmail();
        dto.userDetails = user.getUserDetails();
        dto.authority = user.getAuthorities();

        return dto;
    }

    public UserModel toUser(UserInputDto userDto) {

        var user = new UserModel();

        user.setUserName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApiKey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }
}


