package com.example.WatchNext.security.jwt;

import org.passay.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GeneratePassword {

    public String generateRandomPassword() {

        List rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit,
                        1));

        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8, rules);
        return password;
    }

    public boolean isPasswordValid(String password) {
        boolean isPasswordValid = false;
        PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 16),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            isPasswordValid = true;
        } else {
            isPasswordValid = false;
            final StringBuilder message = new StringBuilder();
            validator.getMessages(result).stream().forEach(message::append);
        }
        return isPasswordValid;
    }
}
