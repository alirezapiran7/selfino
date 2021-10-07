package ir.selfino.android.Interfaces;

import ir.selfino.android.Function.Models.Food;
import ir.selfino.android.Function.Models.Meal;

public interface FoodListener
{
    void cancelFood(Meal meal, Food food);
    void selectFood(Meal meal,Food food);
}
