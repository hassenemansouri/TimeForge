package tn.esprit.user_strategicpartership.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.entity.BlockchainRecord;
import tn.esprit.user_strategicpartership.service.BlockchainService;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

  private final BlockchainService blockchainService;

  public BlockchainController(BlockchainService blockchainService) {
    this.blockchainService = blockchainService;
  }

  @GetMapping
  public List<BlockchainRecord> getBlockchain() {
    return blockchainService.getBlockchain();
  }
}
