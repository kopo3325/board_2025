package kr.ac.kopo.ctc.kopo33.board.domain;

public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private String message;

    public AuthResponse(String accessToken, String tokenType, String message) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getMessage() {
        return message;
    }
}
