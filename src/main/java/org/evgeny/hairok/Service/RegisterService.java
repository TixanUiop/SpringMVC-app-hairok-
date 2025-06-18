package org.evgeny.hairok.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.evgeny.hairok.DTO.ClientDTO;
import org.evgeny.hairok.DTO.MasterProfilesDTO;
import org.evgeny.hairok.DTO.RegisterClientDTO;
import org.evgeny.hairok.DTO.RegisterMasterAccountDTO;
import org.evgeny.hairok.Entity.Client;
import org.evgeny.hairok.Entity.MasterAccount;
import org.evgeny.hairok.Entity.MasterProfiles;
import org.evgeny.hairok.Exception.PhoneExist;
import org.evgeny.hairok.Mapper.ClientToClientDTOMapper;
import org.evgeny.hairok.Mapper.MasterAccountDTOToMasterAccount;
import org.evgeny.hairok.Mapper.MasterProfilesDTOToMasterProfiles;
import org.evgeny.hairok.Mapper.RegisterMapperDTOToEntity;
import org.evgeny.hairok.Repository.RegisterUserRepository;
import org.evgeny.hairok.Utill.StandardPhoneNumber;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {

    private final PasswordEncoder passwordEncoder;

    private final RegisterMapperDTOToEntity registerMapperDTOToEntity;
    private final ClientToClientDTOMapper clientToClientDTOMapper;
    private final MasterAccountDTOToMasterAccount masterAccountDTOToMasterAccount;
    private final MasterProfilesDTOToMasterProfiles masterProfilesDTOToMasterProfiles;


    private final RegisterUserRepository registerUserRepository;

    @Transactional(rollbackOn = Exception.class)
    @SneakyThrows
    public MasterProfilesDTO saveMasterProfileAndAccount(RegisterMasterAccountDTO registerMasterAccountDTO,
                                                         MasterProfilesDTO masterProfilesDTO)

    {
        try {
            if (registerUserRepository.existsByMastersPhone(StandardPhoneNumber.standardizePhone(masterProfilesDTO.getPhone())).isPresent()) {
                log.error("Phone already exists");
                throw new PhoneExist();
            }

            registerMasterAccountDTO.setPassword(passwordEncoder.encode(registerMasterAccountDTO.getPassword()));
            MasterAccount masterAccount = masterAccountDTOToMasterAccount.map(registerMasterAccountDTO);

            String standardNumber = StandardPhoneNumber.standardizePhone(masterProfilesDTO.getPhone());
            masterProfilesDTO.setPhone(standardNumber);
            MasterProfiles masterProfiles = masterProfilesDTOToMasterProfiles.map(masterProfilesDTO);


            masterAccount.setMasterProfiles(masterProfiles);
            Optional<MasterAccount> save = registerUserRepository.save(masterAccount);

            registerUserRepository.save(masterProfiles);


            Long id = save.get().getId();
            String image = saveImage(masterProfilesDTO.getAvatar());
            registerUserRepository.updateById(id, image);

            return masterProfilesDTO;

        }
        catch (IOException e) {
            throw new RuntimeException();
        }

    }


    private String saveImage(MultipartFile multipartFile) throws IOException {

        String imagePath = "images/";
        String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
        Path path = Paths.get(imagePath + fileName);

        Files.createDirectories(path.getParent());
        multipartFile.transferTo(path);

        //path весь с папкой Image/imageName или fileName только имя
        return fileName;
    }


    public boolean checkEmail(String email) {

        if (registerUserRepository.existsByEmail(email).isPresent()) {
            log.error("Email already exists");
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Optional<ClientDTO> save(RegisterClientDTO registerClientDTO) {
        if (registerUserRepository.existsByPhone(StandardPhoneNumber.standardizePhone(registerClientDTO.getPhone()))) {
            log.error("Phone already exists");
            throw new PhoneExist();
        }

        registerClientDTO.setPassword(passwordEncoder.encode(registerClientDTO.getPassword()));
        Client client = registerMapperDTOToEntity.map(registerClientDTO);

        String standardPhone = StandardPhoneNumber.standardizePhone(registerClientDTO.getPhone());
        client.setPhone(standardPhone);

        ClientDTO result = clientToClientDTOMapper.map(registerUserRepository.save(client));
        log.info(result.toString(), "Сохранён в базу данных");
        return Optional.of(result);
    }

}
