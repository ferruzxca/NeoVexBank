package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.BankBranch;
import com.innovex.neovexbank.service.BankBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BankBranchController {

    private final BankBranchService bankBranchService;

    public BankBranchController(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    // GET: /branches
    @GetMapping
    public ResponseEntity<List<BankBranch>> getAllBranches() {
        return ResponseEntity.ok(bankBranchService.getAllBranches());
    }

    // POST: /branches
    @PostMapping
    public ResponseEntity<BankBranch> createBranch(@RequestBody BankBranch branch) {
        return ResponseEntity.ok(bankBranchService.saveBranch(branch));
    }

    // DELETE: /branches/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable Long id) {
        bankBranchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}