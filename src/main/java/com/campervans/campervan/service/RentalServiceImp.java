package com.campervans.campervan.service;

import com.campervans.campervan.exception.RecordNotFoundException;
import com.campervans.campervan.model.RentalEntity;
import com.campervans.campervan.repository.RentalRepository;
import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
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


    @Autowired
    private RentalRepository repository;

    public List<RentalEntity> getAllCampervans(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<RentalEntity> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<RentalEntity>();
        }
    }
//
//    public RentalEntity getCapmervansById(Long id) throws RecordNotFoundException
//    {
//        Optional<RentalEntity> employee = repository.findById(id);
//
//        if(employee.isPresent()) {
//            return employee.get();
//        } else {
//            throw new RecordNotFoundException("No employee record exist for given id");
//        }
//    }


    /////////////////////


    @Override
    public List getAllCampervan() {
        String message = String.format("Service - getAllCampervan");
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        List rentalList = new ArrayList();
        repository.findAll().forEach(rentalList::add);
        Sentry.getContext()
                .recordBreadcrumb(new BreadcrumbBuilder().setMessage("Exit from getAllCampervan").build());
        return rentalList;
    }

    @Override
    public List getPricePerDayBetween(BigDecimal min, BigDecimal max) {
        String message = String.format("Service - getBetweenPrice parameters" + min + max);
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        List rentalList = new ArrayList();
        repository.findByPricePerDayBetween(min, max).forEach(rentalList::add);
        Sentry.getContext()
                .recordBreadcrumb(new BreadcrumbBuilder().setMessage("Exit from getBetweenPrice").build());
        return rentalList;
    }

    @Override
    public List getByCampervansIds(long[] ids) {
        String message = String.format("Service - getByCampervansIds parameters" + ids);
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        if (ids.length == 0) {
            IllegalArgumentException e = new IllegalArgumentException("No id");
            Sentry.capture(e);
            throw e;
        }
        List rentalList = new ArrayList();
        for (long id : ids) {
            if (id < 1) {
                IllegalArgumentException e = new IllegalArgumentException("Invalid id");
                Sentry.capture(e);
                throw e;
            }
            rentalList.add(repository.findAllById(id));
        }
        Sentry.getContext()
                .recordBreadcrumb(new BreadcrumbBuilder().setMessage("Exit from ByCampervansIds").build());
        return rentalList;
    }

    @Override
    public List getLocation(double x, double y) {
        String message = String.format("Service - getLocation parameters " + x + y);
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        if (x < -90 || x > 90) {
            IllegalArgumentException e = new IllegalArgumentException("Longtitude out of limits");
            Sentry.capture(e);
            throw e;
        }
        if (y < -90 || y > 90) {
            IllegalArgumentException e = new IllegalArgumentException("Latitude out of limits");
            Sentry.capture(e);
            throw e;
        }
        Sentry.getContext().clear();
        List rentalList = new ArrayList();
        repository.findLocated(x, y).forEach(rentalList::add);
        return rentalList;
    }

    @Override
    public List getAllCampervanOrderByPricePerDayDESC() {
        String message = String.format("Service - getAllCampervanOrderByPricePerDayDESC");
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        List rentalList = new ArrayList();
        repository.findAllByOrderByPricePerDayDesc().forEach(rentalList::add);
        Sentry.getContext()
                .recordBreadcrumb(
                        new BreadcrumbBuilder()
                                .setMessage("Exit from getAllCampervanOrderByPricePerDayDESC")
                                .build());
        return rentalList;
    }

    @Override
    public List getPage(int limit, int offset) {
        String message = String.format("Service - getPage parameters " + limit + offset);
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        List rentalList = new ArrayList();
        List page = new ArrayList();
        repository.findAll().forEach(rentalList::add);
        if (offset < 0 || offset > rentalList.size()) {
            IllegalArgumentException e = new IllegalArgumentException("Offset out of range");
            Sentry.capture(e);
            throw e;
        }
        if (limit < 0) {
            IllegalArgumentException e = new IllegalArgumentException("Limits out of range");
            Sentry.capture(e);
            throw e;
        }
        for (int i = limit * offset; i < (limit + 1) * offset; i++) {
            if (i >= rentalList.size()) {
                break;
            }
            page.add(rentalList.get(i));
        }
        Sentry.getContext()
                .recordBreadcrumb(new BreadcrumbBuilder().setMessage("Exit from getPage").build());
        return page;
    }

    @Override
    public List getByCampervanID(long id) {
        String message = String.format("Service - getByCampervansID parameters" + id);
        Sentry.getContext().recordBreadcrumb(new BreadcrumbBuilder().setMessage(message).build());
        if (id < 1) {
            IllegalArgumentException e = new IllegalArgumentException("Invalid id");
            Sentry.capture(e);
            throw e;
        }
        List rentalList = new ArrayList();
        repository.findAllById(id).forEach(rentalList::add);
        Sentry.getContext()
                .recordBreadcrumb(new BreadcrumbBuilder().setMessage("Exit from ByCampervansID").build());
        return rentalList;
    }
}
