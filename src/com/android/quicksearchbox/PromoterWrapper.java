/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.quicksearchbox;

import java.util.ArrayList;

/**
 * A promoter that does some promotion or filtering and then delegates
 * to another promoter.
 */
public class PromoterWrapper<C extends SuggestionCursor> implements Promoter<C> {

    /** The next promoter to use. */
    private final Promoter<C> mNextPromoter;

    /**
     * Creates a new AbstractPromoterWrapper.
     *
     * @param nextPromoter The next promoter to use. May be {@code null}.
     */
    public PromoterWrapper(Promoter<C> nextPromoter) {
        mNextPromoter = nextPromoter;
    }

    public void pickPromoted(SuggestionCursor shortcuts,
            ArrayList<C> suggestions, int maxPromoted,
            ListSuggestionCursor promoted) {
        if (promoted.getCount() < maxPromoted && mNextPromoter != null) {
            mNextPromoter.pickPromoted(shortcuts, suggestions, maxPromoted, promoted);
        }
    }

}
