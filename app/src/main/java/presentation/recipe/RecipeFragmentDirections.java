package presentation.recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.example.listreceitasapp.R;

import java.util.HashMap;

public class RecipeFragmentDirections {
    private RecipeFragmentDirections() {
    }

    @NonNull
    public static ActionFirstFragmentToSecondFragment actionFirstFragmentToSecondFragment(
            int idRecipe) {
        return new ActionFirstFragmentToSecondFragment(idRecipe);
    }

    public static class ActionFirstFragmentToSecondFragment implements NavDirections {
        private final HashMap arguments = new HashMap();

        @SuppressWarnings("unchecked")
        private ActionFirstFragmentToSecondFragment(int idRecipe) {
            this.arguments.put("idRecipe", idRecipe);
        }

        @NonNull
        @SuppressWarnings("unchecked")
        public ActionFirstFragmentToSecondFragment setIdRecipe(int idRecipe) {
            this.arguments.put("idRecipe", idRecipe);
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        @NonNull
        public Bundle getArguments() {
            Bundle __result = new Bundle();
            if (arguments.containsKey("idRecipe")) {
                int idRecipe = (int) arguments.get("idRecipe");
                __result.putInt("idRecipe", idRecipe);
            }
            return __result;
        }

        @Override
        public int getActionId() {
            return R.id.action_RecipeFragment_to_DetailsFragment;
        }

        @SuppressWarnings("unchecked")
        public int getIdRecipe() {
            return (int) arguments.get("idRecipe");
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            ActionFirstFragmentToSecondFragment that = (ActionFirstFragmentToSecondFragment) object;
            if (arguments.containsKey("idRecipe") != that.arguments.containsKey("idRecipe")) {
                return false;
            }
            if (getIdRecipe() != that.getIdRecipe()) {
                return false;
            }
            if (getActionId() != that.getActionId()) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + getIdRecipe();
            result = 31 * result + getActionId();
            return result;
        }

        @Override
        public String toString() {
            return "ActionFirstFragmentToSecondFragment(actionId=" + getActionId() + "){"
                    + "idRecipe=" + getIdRecipe()
                    + "}";
        }
    }
}