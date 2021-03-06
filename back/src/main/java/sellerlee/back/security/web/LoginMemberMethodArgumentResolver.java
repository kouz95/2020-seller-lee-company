package sellerlee.back.security.web;

import static org.springframework.web.context.request.RequestAttributes.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import sellerlee.back.member.domain.IllegalLoginException;
import sellerlee.back.member.domain.MemberRepository;
import sellerlee.back.security.core.LoginMember;

@Component
public class LoginMemberMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String MEMBER_NICKNAME_ATTRIBUTE = "authorizedNickname";

    private final MemberRepository memberRepository;

    public LoginMemberMethodArgumentResolver(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String nickname = (String)webRequest.getAttribute(MEMBER_NICKNAME_ATTRIBUTE, SCOPE_REQUEST);

        if (StringUtils.isBlank(nickname)) {
            return new AuthenticationException("인증된 사용자가 존재하지 않습니다.");
        }
        try {
            return memberRepository.findOptionalMemberByNickname(nickname)
                    .orElseThrow(() -> new IllegalLoginException("닉네임이 일치하는 회원이 존재하지 않습니다."));
        } catch (Exception e) {
            throw new AuthenticationException("비정상적인 로그인입니다.");
        }
    }
}
