package com.innovex.neovexbank.service;

import com.innovex.neovexbank.model.BankBranch;
import com.innovex.neovexbank.repository.BankBranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBranchService {

    private final BankBranchRepository bankBranchRepository;

    public BankBranchService(BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    public List<BankBranch> getAllBranches() {
        return bankBranchRepository.findAll();
    }

    public BankBranch saveBranch(BankBranch branch) {
        return bankBranchRepository.save(branch);
    }

    public void deleteBranch(Long id) {
        bankBranchRepository.deleteById(id);
    }
}