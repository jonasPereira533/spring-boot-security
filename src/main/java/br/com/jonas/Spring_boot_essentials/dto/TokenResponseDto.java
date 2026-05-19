package br.com.jonas.Spring_boot_essentials.dto;

public record TokenResponseDto (String token, long expiresIn) {
}
