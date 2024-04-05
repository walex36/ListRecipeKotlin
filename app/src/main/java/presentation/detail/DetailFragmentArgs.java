package presentation.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;

import java.util.HashMap;

public class DetailFragmentArgs implements NavArgs {
    private final HashMap arguments = new HashMap();

    private DetailFragmentArgs() {
    }

    @SuppressWarnings("unchecked")
    private DetailFragmentArgs(HashMap argumentsMap) {
        this.arguments.putAll(argumentsMap);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static DetailFragmentArgs fromBundle(@NonNull Bundle bundle) {
        DetailFragmentArgs __result = new DetailFragmentArgs();
        bundle.setClassLoader(DetailFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("idRecipe")) {
            int idRecipe;
            idRecipe = bundle.getInt("idRecipe");
            __result.arguments.put("idRecipe", idRecipe);
        } else {
            throw new IllegalArgumentException("Required argument \"idRecipe\" is missing and does not have an android:defaultValue");
        }
        return __result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static DetailFragmentArgs fromSavedStateHandle(
            @NonNull SavedStateHandle savedStateHandle) {
        DetailFragmentArgs __result = new DetailFragmentArgs();
        if (savedStateHandle.contains("idRecipe")) {
            int idRecipe;
            idRecipe = savedStateHandle.get("idRecipe");
            __result.arguments.put("idRecipe", idRecipe);
        } else {
            throw new IllegalArgumentException("Required argument \"idRecipe\" is missing and does not have an android:defaultValue");
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    public int getIdRecipe() {
        return (int) arguments.get("idRecipe");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle toBundle() {
        Bundle __result = new Bundle();
        if (arguments.containsKey("idRecipe")) {
            int idRecipe = (int) arguments.get("idRecipe");
            __result.putInt("idRecipe", idRecipe);
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public SavedStateHandle toSavedStateHandle() {
        SavedStateHandle __result = new SavedStateHandle();
        if (arguments.containsKey("idRecipe")) {
            int idRecipe = (int) arguments.get("idRecipe");
            __result.set("idRecipe", idRecipe);
        }
        return __result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DetailFragmentArgs that = (DetailFragmentArgs) object;
        if (arguments.containsKey("idRecipe") != that.arguments.containsKey("idRecipe")) {
            return false;
        }
        if (getIdRecipe() != that.getIdRecipe()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + getIdRecipe();
        return result;
    }

    @Override
    public String toString() {
        return "SecondFragmentArgs{"
                + "idRecipe=" + getIdRecipe()
                + "}";
    }

    public static final class Builder {
        private final HashMap arguments = new HashMap();

        @SuppressWarnings("unchecked")
        public Builder(@NonNull DetailFragmentArgs original) {
            this.arguments.putAll(original.arguments);
        }

        @SuppressWarnings("unchecked")
        public Builder(int idRecipe) {
            this.arguments.put("idRecipe", idRecipe);
        }

        @NonNull
        public DetailFragmentArgs build() {
            DetailFragmentArgs result = new DetailFragmentArgs(arguments);
            return result;
        }

        @NonNull
        @SuppressWarnings("unchecked")
        public Builder setIdRecipe(int idRecipe) {
            this.arguments.put("idRecipe", idRecipe);
            return this;
        }

        @SuppressWarnings({"unchecked","GetterOnBuilder"})
        public int getIdRecipe() {
            return (int) arguments.get("idRecipe");
        }
    }
}
