package com.workingtitle.makeit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Maricarla on 2016-06-13.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Search by Ingredients", "Search by Recipes", "Saved Recipes", "Search History" };
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddIngredientsFragment searchIngredientsTab = new AddIngredientsFragment();
                return searchIngredientsTab;
            case 1:
                SearchRecipeNameFragment searchRecipeTab = new SearchRecipeNameFragment();
                return searchRecipeTab;
            case 2:
                SavedRecipesFragment savedRecipesFragment = new SavedRecipesFragment();
                return savedRecipesFragment;
            case 3:
                SearchHistoryFragment searchHistoryFragment = new SearchHistoryFragment();
                return searchHistoryFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

        // getDrawable(int i) is deprecated, use getDrawable(int i, Theme theme) for min SDK >=21
        // or ContextCompat.getDrawable(Context context, int id) if you want support for older versions.
        // Drawable image = context.getResources().getDrawable(iconIds[position], context.getTheme());
        // Drawable image = context.getResources().getDrawable(imageResId[position]);

//        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
    }

//    public View getTabView(int position){
////        View view = LayoutInflater.from(context).inflate(R.layout.home_page_tab, null);
////        TextView textView = (TextView) view.findViewById(R.id.textView);
////        textView.setText(tabTitles[position]);
////        ImageView img = (ImageView) view.findViewById(R.id.imgView);
////        img.setImageResource(imageResId[position]);
//
//        return view;
//    }
}
