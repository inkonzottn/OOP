package com.lab4.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isCompleted = false;
    private Integer totalHoursSpent = 0;
    private Date createdAt;
    private Date finishedAt = null;

    private Customer customer;
    private Task task;
    private Manager manager;

    @ToString.Exclude // щоб уникнути рекурсії
    private List<Developer> developers;

    // розрахунок ціни проєкту
    public void calculateAndSetPrice() {
        BigDecimal totalCost = BigDecimal.ZERO;
        if (developers != null) {
            for (Developer dev : developers) {
                // припускаємо, що всі розробники працювали однаковий час

                totalCost = totalCost.add(dev.getHourlyRate());
            }
            // ціна = (сума ставок розробників за годину) * витрачені години
            this.price = totalCost.multiply(new BigDecimal(totalHoursSpent));
        }
    }
}
