package pl.tkaras.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.tkaras.security.service.AppUserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final AppUserDetailsServiceImpl appUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String parsedJwtToken = parseJwt(request);
        if(parsedJwtToken == null){
            filterChain.doFilter(request, response);
            return;
        }
        try{
            String username = jwtUtils.getUsernameFromJwtToken(parsedJwtToken);
            UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (Exception e){
            throw new IllegalStateException("Cannot set user authentication");
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader(jwtUtils.getTokenHeader());

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith(jwtUtils.getTokenPrefix())){
            return headerAuth.replace(jwtUtils.getTokenPrefix(), "");
        }
        return null;
    }
}