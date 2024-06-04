package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAppController extends AbstractController<UserApp> {

    @Autowired
    private UserAppService service;

    @Autowired
    private GameTableService tableService;

    private final UserAppRepository userAppRepository;

    @GetMapping("/email/{email}")
    public ResponseEntity<UserApp> getUserByEmail(@PathVariable String email, HttpServletRequest request) throws AccessDeniedException {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (username.equals(email) || roles.equals("[ROLE_ADMIN]")) {
            return ResponseEntity.ok(userAppRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("email " + email +" not found"))
            );
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");
        }
    }


    @GetMapping("/all")
//    public List<UserApp> getAll(HttpServletRequest request) throws AccessDeniedException {
        public ResponseEntity<List<UserApp>> getAll(HttpServletRequest request) throws AccessDeniedException {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roles);
        if(roles.equals("[ROLE_ADMIN]")) {
            //return userAppRepository.findAll();
            List<UserApp> entityList = userAppRepository.findAll();

            return new ResponseEntity<>(entityList, HttpStatus.OK);
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");

        }
    }
}
