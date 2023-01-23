package com.insacvl.sefkim_flickr.adapters;
/**
* @Author : ZKIM Youssef
*/
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.insacvl.sefkim_flickr.R;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                    OnBoardingAdapter                                            *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * OnboardingAdapter is a class that extends PagerAdapter. It is used to handle the different      *
 * layouts for an onboarding experience.                                                           *
 *                                                                                                 *
 * The class has a context which is used to access system services. It also has an array of        *
 * integers (layouts) which contains the layout resources for each onboarding screen               *
 *                                                                                                 *
 * The constructor for the class takes in a Context object. It sets the context variable to the    *
 * passed in context.                                                                              *
 *                                                                                                 *
 * The getCount() method returns the number of layouts in the layouts array. This is used to       *
 * determine how many pages the onboarding experience will have.                                   *
 *                                                                                                 *
 * The isViewFromObject() method checks if the passed in view and object are the same. This is used*
 * to determine if a view should be reused or not.                                                 *
 *                                                                                                 *
 * The instantiateItem() method inflates the layout at the given position and adds it to the       *
 * container. It also sets the tag of the view to the position it is in. It then returns the view. *
 *                                                                                                 *
 * The destroyItem() method removes the passed in object (which is a ConstraintLayout) from the    *
 * container. This is used to clean up and remove old views that are no longer needed.             *
 *                                                                                                 *
 *=================================================================================================*

 */

public class OnboardingAdapter extends PagerAdapter {

    private Context context;
    private int[] layouts = {
            R.layout.onboarding_1,
            R.layout.onboarding_2,
            R.layout.onboarding_3,
            R.layout.onboarding_4
    };

    public OnboardingAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return layouts.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layouts[position], container, false);
        view.setTag(position);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
