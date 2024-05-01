package com.vinnnm.ecommercesystem.service.implement;

import com.vinnnm.ecommercesystem.daos.BrandRepository;
import com.vinnnm.ecommercesystem.dtos.BrandDTO;
import com.vinnnm.ecommercesystem.entity.Brand;
import com.vinnnm.ecommercesystem.service.BrandService;
import com.vinnnm.ecommercesystem.util.Helper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandServiceImplement implements BrandService {
    @Autowired
    private final BrandRepository brandRepository;

    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public boolean addNewBrand(BrandDTO brandDTO) {
        try {
            brandDTO.setCreatedAt(Helper.getCurrentTimestamp());
            if(!isBrandExists(brandDTO.getName())) {
                brandRepository.save(modelMapper.map(brandDTO, Brand.class));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BrandDTO> getAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandDTO> categoryDTOS = new ArrayList<>();
        if(brands.isEmpty()) {
            return null;
        } else {
            for (Brand brand : brands) {
                BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
                brandDTO.setCreatedAtDate(Helper.convertTimestampToDate(brand.getCreatedAt()));
                categoryDTOS.add(brandDTO);
            }
        }
        return categoryDTOS;
    }

    @Override
    public boolean delete(long id) {
        if (brandRepository.existsById(id)) {
            try {
                brandRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public BrandDTO getByName(String name) {
        BrandDTO brandDTO = modelMapper.map(brandRepository.findByName(name), BrandDTO.class);
        brandDTO.setCreatedAtDate(Helper.convertTimestampToDate(brandDTO.getCreatedAt()));
        return brandDTO;
    }

    @Override
    public boolean addNewBrandByName(String name) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(name);
        return addNewBrand(brandDTO);
    }

    @Override
    public boolean isBrandExists(String name) {
        Brand brand = brandRepository.findByName(name);
        return brand != null;
    }
}
