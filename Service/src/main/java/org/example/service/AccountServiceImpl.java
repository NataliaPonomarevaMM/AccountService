package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.AccountEntity;
import org.example.dao.AccountRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Cacheable(cacheNames="amounts")
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Long getAmount(Integer id) {
        AccountEntity accountEntity = findAccountById(id);
        return accountEntity.getAmount();
    }

    @Override
    @CachePut(cacheNames="amounts", key="#id")
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Long addAmount(Integer id, Long value) {
        AccountEntity accountEntity = findAccountById(id);
        accountEntity.addAmount(value);
        accountRepository.save(accountEntity);
        return accountEntity.getAmount();
    }

    private AccountEntity findAccountById(Integer id) {
        return accountRepository
                .findById(id)
                .orElse(new AccountEntity(id, 0L));
    }
}
