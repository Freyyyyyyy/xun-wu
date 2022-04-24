package com.cpt202.xunwu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cpt202.xunwu.bean.ComResult;
import com.cpt202.xunwu.model.Product;
import com.cpt202.xunwu.repository.ProductRepo;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Service
public class ViewProdInfoService {
    
    @Autowired
    private ProductRepo productRepo;
    
    //条件搜索
    public Page<Product> cdSearch(int page,
                                  int size,
                                  int sortC,
                                  String keyword,
                                  Integer prodCategoryId,
                                  Date dateStart,
                                  Date dateEnd,
                                  Double priceStart,
                                  Double priceEnd,
                                  Long sizeStart,
                                  Long sizeEnd){
        Sort sort = Sort.by("productId");
        if(sortC == 1){
            sort = Sort.by("creatDate").descending();
        }else if(sortC == 2){
            sort = Sort.by("productSaleVolume").descending();
        }else if(sortC == 3){
            sort = Sort.by("productPrice").ascending();
        }else if(sortC == 4){
            sort = Sort.by("productPrice").descending();
        }                              
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepo.findAll(
            (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.hasLength(keyword)){
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("productName"), "%\\"+keyword+"%"),
                                                       criteriaBuilder.like(root.get("productDescription"), "%\\"+keyword+"%")));
                }
                if(prodCategoryId != null && prodCategoryId != 0){
                    predicates.add(criteriaBuilder.equal(root.get("productCategoryId"), prodCategoryId));
                }
                if(dateStart != null && dateEnd != null){
                    predicates.add(criteriaBuilder.between(root.get("creatDate"), dateStart, dateEnd));
                }
                if(priceStart != null && priceEnd != null){
                    predicates.add(criteriaBuilder.between(root.get("productPrice"), priceStart, priceEnd));
                }
                if(sizeStart != null && sizeEnd != null){
                    predicates.add(criteriaBuilder.between(root.get("productSizeDesc"), sizeStart, sizeEnd));
                }                
                predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("productStatus"), 1),
                                                  criteriaBuilder.equal(root.get("productStatus"), 2)));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
            , pageable);
    }


    public ComResult viewProdDetail(long productId){
        ComResult result = new ComResult<>();
        try{
            result.setData(productRepo.findById(productId));
            result.setCode(200);
            result.setMessage("商品详情获取成功");
            return result;
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
            return result;
        }
    }

    public Page<Product> viewMyProd(int page,
                                    int size,
                                    Long userId){
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(
            (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if(userId != null){
                    predicates.add(criteriaBuilder.equal(root.get("productPublishUserId"), userId));
                }                
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
            , pageable);
    }

}

