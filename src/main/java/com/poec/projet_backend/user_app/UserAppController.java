package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import com.poec.projet_backend.domaine.game_table.GameTable;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import com.poec.projet_backend.util.Patcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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
        public ResponseEntity<List<UserAppDTO>> getAll(HttpServletRequest request) throws AccessDeniedException {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        System.out.println(roles);
        if(roles.equals("[ROLE_ADMIN]")) {
            List<UserApp> entityList = userAppRepository.findAll();
            List<UserAppDTO> userAppDTOList = entityList.stream().map(UserAppDTO::mapFromEntity).toList();
            return new ResponseEntity<>(userAppDTOList, HttpStatus.OK);
        } else {
            throw new AccessDeniedException("UserApp does not have the correct rights to access to this resource");
        }
    }

    @GetMapping("/personal")
    public ResponseEntity<UserAppDTO> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = userAppRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(UserAppDTO.mapFromEntity(userApp));
    }

    @GetMapping("/get/user-invited/tableId={tableId}")
    public ResponseEntity<List<UserAppShortDTO>> getUserInvitedList(@PathVariable("tableId") Long tableId) {
        GameTable tableFound = tableService.getById(tableId);
        List<UserApp> tableUserInvitedList = tableFound.getUsers_invitation();
        List<UserAppShortDTO> tableUserInvitatedDTOList = tableUserInvitedList.stream().map(UserAppShortDTO::mapFromEntity).toList();
        return new ResponseEntity<>(tableUserInvitatedDTOList, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserAppDTO> patchUser(@RequestBody UserApp incompleteUser, @PathVariable Long id) {
        UserApp foundUser = service.getById(id);

        try {
            Patcher.elementPatcher(foundUser, incompleteUser);
            service.add(foundUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(UserAppDTO.mapFromEntity(foundUser), HttpStatus.OK);
    }

    @GetMapping("/getinvitations/{userId}")
    public ResponseEntity<UserAppDTOWithInvitations> getUserWithInvitations(@PathVariable Long userId) {
        UserApp user = service.getById(userId);

        return new ResponseEntity<>(UserAppDTOWithInvitations.mapFromEntity(user), HttpStatus.OK);
    }
}
