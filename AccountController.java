@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<Respuesta> getBalance(@PathVariable String accountNumber, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return ResponseEntity.ok(accountService.consultarSaldo(accountNumber, username));
    }
}