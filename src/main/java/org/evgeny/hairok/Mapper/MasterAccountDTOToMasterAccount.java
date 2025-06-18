package org.evgeny.hairok.Mapper;

import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.evgeny.hairok.Entity.MasterAccount;
import org.springframework.stereotype.Component;

@Component
public class MasterAccountDTOToMasterAccount implements Mapper<RegisterMasterAccountDTO, MasterAccount> {
    @Override
    public MasterAccount map(RegisterMasterAccountDTO registerMasterAccountDTO) {
        return MasterAccount.builder()
                .email(registerMasterAccountDTO.getEmail())
                .password(registerMasterAccountDTO.getPassword())
                .avatar("Default")
                .build();
    }
}
