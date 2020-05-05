package mk.ukim.finki.projectfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mk.ukim.finki.projectfood.model.idclasses.FoodComponentId;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data

@Table(name = "food_component")
public class FoodComponent implements Serializable {

    @EmbeddedId
    private FoodComponentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private FoodsShowView food;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("componentId")
    @JoinColumn(name = "component_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Component component;

    @Column(name = "wastestream_name")
    private String wasteStreamName;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "component_name")
    private String componentName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodComponent that = (FoodComponent) o;
        return Objects.equals(food, that.food) &&
                Objects.equals(component, that.component) &&
                Objects.equals(wasteStreamName, that.wasteStreamName) &&
                Objects.equals(foodName, that.foodName) &&
                Objects.equals(componentName, that.componentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, component, wasteStreamName, foodName, componentName);
    }
}
