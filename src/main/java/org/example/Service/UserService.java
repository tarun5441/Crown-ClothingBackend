package org.example.Service;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TokenService tokenService;

//    public UserService(UserRepository userRepo) {
//        this.userRepo = userRepo;
//
//    }

    public User saveuser(User obj) {
        User userfinal = userRepo.save(obj);
        System.out.println(userfinal);
        return userfinal;
    }

    public List<User> getallusers(){
        return userRepo.findAll();
    }

    public Boolean getuserbyemail(String email){
        Optional<User> user = Optional.ofNullable(userRepo.getuserbyemail(email));
        if(!user.isEmpty()) return true;
        return false;
    }
    public HashMap<String,Object> userLogin(String name,String password){
        boolean foundUsers = getuserbyemail(name);
        HashMap<String,Object> response= new HashMap<>();
        if(foundUsers) {
            User userObj = userRepo.getuserbyemail(name);
            if(userObj.getPassword().equals(password)){
                response.put("message","Successfully logged in");
                response.put("data",userObj);
                response.put("token",tokenService.createTokenFunction(userObj.getId()));
                return response;
            }
        }
        response.put("message","Authentication failed");
        return response;
    }

}

