package mrs.config;

import mrs.domain.service.user.ReservationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ReservationUserDetailsService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 認証対象外のパス
				.antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**")
				.permitAll()
				// その他のリクエストは認証が必要
				.antMatchers("/**")
				.authenticated()
				.and()
			.formLogin()
				// ログインフォームのパス
				.loginPage("/loginForm")
				// ログイン処理のパス
				.loginProcessingUrl("/login")
				 // ログインフォームで使用するユーザー名のinput name
				.usernameParameter("loginId")
				// ログインフォームで使用するパスワードのinput name
				.passwordParameter("password")
				// ログイン成功時の遷移先
				.defaultSuccessUrl("/rooms", true)
				// ログイン失敗時の遷移先
				.failureUrl("/loginForm?error=true")
				.permitAll();
//		http.headers().xssProtection().frameOptions().contentTypeOptions().cacheControl().and()
//				.authorizeRequests()
//				// 認証対象外のパスを設定する
//				.antMatchers("/", "/login", "/registration/**", "/css/**", "/js/**", "/img/**")
//				// 上記パスへのアクセスを許可する
//				.permitAll()
//				// その他のリクエストは認証が必要
//				.anyRequest().authenticated().and().formLogin()
//				// ログインフォームのパス
//				.loginPage("/")
//				// ログイン処理のパス
//				.loginProcessingUrl("/login")
//				// ログイン成功時の遷移先
//				.defaultSuccessUrl("/menu")
//				// ログイン失敗時の遷移先
//				.failureUrl("/login-error")
//				// ログインフォームで使用するユーザー名のinput name
//				.usernameParameter("empNo")
//				// ログインフォームで使用するパスワードのinput name
//				.passwordParameter("password").permitAll().and().rememberMe()
//				.tokenValiditySeconds(86400) // 1ヶ月（秒）
//				.and().logout()
//				// ログアウトがパス(GET)の場合設定する（CSRF対応）
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				// ログアウトがPOSTの場合設定する
//				// .logoutUrl("/logout")
//				// ログアウト後の遷移先
//				.logoutSuccessUrl("/")
//				// セッションを破棄する
//				.invalidateHttpSession(true)
//				// ログアウト時に削除するクッキー名
//				.deleteCookies("JSESSIONID", "remember-me").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
