package co.alexdev.winy.core.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import javax.inject.Singleton;

import co.alexdev.winy.feature.ui.product.wine.uimodel.PairedWinesViewModel;
import co.alexdev.winy.feature.ui.product.wine.uimodel.ProductMatchesViewModel;

@Singleton
public class DiffCallbacks {

    public static class WinesDiff extends DiffUtil.ItemCallback<ProductMatchesViewModel> {

        @Override
        public boolean areItemsTheSame(@NonNull ProductMatchesViewModel oldItem,
                                       @NonNull ProductMatchesViewModel newItem) {
            return oldItem.id== newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductMatchesViewModel oldItem,
                                          @NonNull ProductMatchesViewModel newItem) {
            return oldItem.equals(newItem);
        }
    }

    public static class PairedWinesDiff extends DiffUtil.ItemCallback<PairedWinesViewModel> {

        @Override
        public boolean areItemsTheSame(@NonNull PairedWinesViewModel oldItem,
                                       @NonNull PairedWinesViewModel newItem) {
            return oldItem.description.equals(newItem.description);
        }

        @Override
        public boolean areContentsTheSame(@NonNull PairedWinesViewModel oldItem,
                                          @NonNull PairedWinesViewModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
