package com.example.SpringSecurity.SpringSecurityApplication.service;

import com.example.SpringSecurity.SpringSecurityApplication.entities.Session;
import com.example.SpringSecurity.SpringSecurityApplication.entities.User;
import com.example.SpringSecurity.SpringSecurityApplication.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateSession(User user, String refreshToken){

        List<Session> userSessions = sessionRepository.findByUser(user);

        if(userSessions.size() == 2){
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();

            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken){

        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not Found for refreshToken: "+refreshToken));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

}
