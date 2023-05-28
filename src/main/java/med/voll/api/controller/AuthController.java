package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.config.security.JWTService;
import med.voll.api.config.security.TokenDataJwt;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid UserData user){
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = jwtService.tokenGenerate((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDataJwt(tokenJWT));
    }
}
