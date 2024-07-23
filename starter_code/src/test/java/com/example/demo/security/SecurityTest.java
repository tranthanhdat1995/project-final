package com.example.demo.security;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityTest {
    private final UserRepository userRepo = mock(UserRepository.class);
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Before
    public void setUp() {
        userDetailsServiceImpl = new UserDetailsServiceImpl(userRepo);
        com.example.demo.TestUtils.injectObjects(userDetailsServiceImpl, "userRepository", userRepo);
    }

    @Test
    public void testLoadUserByUsername() {
        String username = "test";
        User user = new User();
        user.setUsername(username);
        String password = "password";
        user.setPassword(password);
        user.setId(0L);
        when(userRepo.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        assertNotNull(userDetails);
        Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
        assertNotNull(authorityCollection);
        assertEquals(0, authorityCollection.size());
        assertEquals(password, userDetails.getPassword());
        assertEquals(username, userDetails.getUsername());
    }
}
