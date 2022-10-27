package com.sinem.graphql.mutation;

import com.sinem.graphql.model.UserProfileInput;
import com.sinem.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput userProfileInput){
        userProfileService.saveInput(userProfileInput);
        return true;
    }


}
