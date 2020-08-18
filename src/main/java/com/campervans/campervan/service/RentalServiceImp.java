package com.campervans.campervan.service;

import com.campervans.campervan.exception.RecordNotFoundException;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImp implements IRentalService{

    private static Logger logger = LoggerFactory.getLogger(RentalServiceImp.class);


    @Autowired
    private RentalRepository repository;

    public List<RentalEntity> getPagedCampervans(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<RentalEntity> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<RentalEntity>();
        }
    }

    public List<RentalEntity> getSortBy( String sortBy) {
        Sort sortOrder = Sort.by("vehicleYear");

        List<RentalEntity> list = (List<RentalEntity>) repository.findAll(sortOrder);

        return list;
    }

    public RentalEntity getCampervanById(Long id) throws RecordNotFoundException
    {
        Optional<RentalEntity> rental = repository.findById(id);

        if(rental.isPresent()) {
            return rental.get();
        } else {
            throw new RecordNotFoundException("No rental record exist for given id");
        }
    }

    @Override
    public List getNearLocation(double x, double y) {

        logger.info("Input latitude and longitude are: {} {}", x , y);

        List rentalList = new ArrayList();
        repository.findNearLocation(x, y).forEach(rentalList::add);
        return rentalList;
    }


    public List getCampervanByIds(long[] ids) throws RecordNotFoundException
    {
        if(ids.length == 0){
            throw new RecordNotFoundException("No rental record exist for given id");
        }

                List list = new ArrayList();
        for(long id : ids){
            if(id < 1){
                throw new RecordNotFoundException("Invalid id");
            }
            list.add(repository.findAllById(id));
        }
        return  list;

    }

    @Override
    public List getPriceMinMax(BigDecimal min, BigDecimal max) {

        List rentalList = new ArrayList();
        repository.findByPricePerDayBetween(min, max).forEach(rentalList::add);

        return rentalList;
    }


}
