package io.github.ocirne.rosetta.ingredientsmerger;

import io.github.ocirne.rosetta.ingredientsmerger.given.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IngredientsMergerProcedural implements IngredientsMerger {

    @Override
    public List<Ingredient> merge(List<Ingredient> list1, List<Ingredient> list2) {
        Map<String, Integer> map = new HashMap<>();
        sumAllIngredients(list1, map);
        sumAllIngredients(list2, map);
        List<Ingredient> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(new Ingredient(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    private void sumAllIngredients(List<Ingredient> list, Map<String, Integer> map) {
        for (Ingredient ingredient : list) {
            String key = ingredient.getName();
            int value = ingredient.getAmount();
            int total = map.getOrDefault(key, 0);
            map.put(key, total + value);
        }
    }
}
