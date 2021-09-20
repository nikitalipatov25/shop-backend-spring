package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private List<UUID> products;
    private String orderType;
    /*
    На случай, ЕСЛИ ПОКУПАТЕЛЬ указывает другой адрес доставки или номер телефона при заказе,
    или если не хочет сохранять эти данные,
    тлт если заказ заберет другой человек -
    все эти данные хранятся в поле экстра
    */
    private String fullName;
    private String address;
    private String phoneNumber;
    private boolean changeData;
    private boolean saveData;
}
