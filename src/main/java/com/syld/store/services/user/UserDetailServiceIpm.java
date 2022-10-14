package com.syld.store.services.user;

import com.syld.store.entities.User;
import com.syld.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceIpm implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw  new UsernameNotFoundException("User not found!");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRole().getRole_name()));
        return  new org.springframework.security.core.userdetails.User(username,user.get().getPassword(),authorities);
    }


}
