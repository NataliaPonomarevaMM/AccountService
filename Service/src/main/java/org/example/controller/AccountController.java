package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.monitoring.MetricsStorage;
import org.example.model.AddAmountRequest;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @Autowired
    private MetricsStorage metricsStorage;

    @GetMapping("/{id}")
    public Long getAmountById(@PathVariable Integer id) {
        metricsStorage.getGetRequests().mark();
        return accountService.getAmount(id);
    }

    @PostMapping
    public void addAmount(@RequestBody AddAmountRequest request) {
        metricsStorage.getPostRequests().mark();
        accountService.addAmount(request.getId(), request.getAmount());
    }
}