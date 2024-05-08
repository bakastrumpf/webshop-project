package com.iktpreobuka.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.VoucherEntity;

import java.util.List;

// TODO 4.2 • 4.2 u paketu com.iktpreobuka.project.repositories napraviti interfejs VoucherRepository

public interface VoucherRepository extends CrudRepository<VoucherEntity, Integer>{


    static List<VoucherEntity> findByExpiryDate(VoucherEntity date) {
        return null;
    }

    void delete(voucherEntity voucher);

    List<VoucherEntity> findByBuyerId(Integer buyerId);

    List<VoucherEntity> findByOfferId(Integer offerId);
}
