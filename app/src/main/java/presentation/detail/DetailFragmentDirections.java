package presentation.detail;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

import com.example.listreceitasapp.R;


public class DetailFragmentDirections {
  private DetailFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionDetailFragmentToRecipeFragment() {
    return new ActionOnlyNavDirections(R.id.action_DetailsFragment_to_RecipeFragment);
  }
}
