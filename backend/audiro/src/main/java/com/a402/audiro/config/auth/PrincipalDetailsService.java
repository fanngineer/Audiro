/*
* Authenrication를 만들어서 session에 넣어주는 역할
*
* OAuth 로그인에는 필요없음!!!!! - 추후에 자체 로그인 구현 시에 필요하면 쓰려고 둠
* */

package com.a402.audiro.config.auth;

////security설정에서 /login 요청이 오면 자동으로 userDetailsService 타입으로 IoC 되어있는 함수가 실행
//@Service
//public class PrincipalDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User userEntity = userRepository.findByEmail(email);
//        if(userEntity != null){
//            return new PrincipalDetails(userEntity);
//        }
//        return null;
//    }
//}
