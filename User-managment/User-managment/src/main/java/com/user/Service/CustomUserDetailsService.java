package com.user.Service;

import com.user.Model.CustomUserDetails;
import com.user.Model.User;
import com.user.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {


        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(userName);
            CustomUserDetails customUserDetails = null;
            if (user != null) {
                customUserDetails = new CustomUserDetails();
                customUserDetails.setUser(user);
            } else {
                throw new UsernameNotFoundException("User Not exists with this username");
            }
            return customUserDetails;
        }
        private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    true, true, true, true, authorities);
        }
    }

