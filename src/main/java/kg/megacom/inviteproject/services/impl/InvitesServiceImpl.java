package kg.megacom.inviteproject.services.impl;

import kg.megacom.inviteproject.dao.InvitesRepo;
import kg.megacom.inviteproject.mappers.InvitesMapper;
import kg.megacom.inviteproject.models.dto.InvitesDto;
import kg.megacom.inviteproject.models.entity.Invites;
import kg.megacom.inviteproject.models.entity.Status;
import kg.megacom.inviteproject.services.InvitesService;
import kg.megacom.inviteproject.services.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;

@Service
public class InvitesServiceImpl implements InvitesService {

    @Autowired
    private SubscribersService subscribersService;
    @Autowired
    private  InvitesRepo invitesRepo;
    @Override
    public InvitesDto send(InvitesDto invitesDto) {

        invitesDto.setSender(subscribersService.saveIfNotExists(invitesDto.getSender()));
        invitesDto.setReceiver(subscribersService.saveIfNotExists(invitesDto.getReceiver()));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);

        //   01.06.2021   00:00:00.000
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        invitesDto.setStatus(Status.ACTIVE);


        if (!invitesDto.getReceiver().isActive()) {
            System.out.println("Извините, абонент "+ invitesDto.getReceiver().getPhone()+" отключил возможность приглашения");

        }
        if (invitesDto.getStatus().equals(Status.ACCEPTED)){
            System.out.println("Извините, абонент "+ invitesDto.getReceiver().getPhone()+" уже активировал приглашения");
        }
        if (invitesRepo.countAllBySenderAndStartDateAfter(invitesDto.setSender(),invitesDto.setStartDate();))



        else {
            System.out.println("Абонент "+ invitesDto.getReceiver().getPhone()+" принял ваш приглашения");
        }

        Invites invites = InvitesMapper.INSTANCE.toInvite(invitesDto);
        return InvitesMapper.INSTANCE.toInviteDto(invites);

        }

    }
