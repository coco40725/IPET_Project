package com.ipet.core.config;



/**
 * @author Yu-Jing
 * @create 2023-01-23-上午 01:12
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    private UserDetailsService userDetailsService;
//    private DataSource dataSource;
//
//    @Autowired
//    public void setUserDetailsService(UserDetailsService userDetailsService){
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Autowired
//    public void setDataSource(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//
//    /*
//    使用 "remember me" 功能， 必須建立 token table
//    **/
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        // 設定 datasource，以建立 token table
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 自動創建 存token 的 table，先創建不使用自動創建
//        jdbcTokenRepository.setCreateTableOnStartup(false);
//        return jdbcTokenRepository;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return new ProviderManager(Arrays.asList(authenticationProvider()));
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
