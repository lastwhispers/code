package cn.itcast.account.web;

import cn.itcast.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 虎哥
 */
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/{userId}/{money}")
    public ResponseEntity<Void> debit(@PathVariable("userId") String userId, @PathVariable("money") Integer money){
        accountService.debit(userId, money);
        return ResponseEntity.noContent().build();
    }
}
