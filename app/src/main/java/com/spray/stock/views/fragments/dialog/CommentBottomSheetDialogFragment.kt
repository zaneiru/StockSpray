package com.spray.stock.views.fragments.dialogimport android.os.Bundleimport android.util.Logimport androidx.fragment.app.Fragmentimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport com.google.android.material.bottomsheet.BottomSheetDialogFragmentimport com.spray.stock.Rimport dagger.hilt.android.AndroidEntryPoint@AndroidEntryPointclass CommentBottomSheetDialogFragment : BottomSheetDialogFragment() {    override fun getTheme(): Int = R.style.BottomSheetDialogTheme    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)    }    override fun onCreateView(        inflater: LayoutInflater, container: ViewGroup?,        savedInstanceState: Bundle?    ): View? {        // Inflate the layout for this fragment        return inflater.inflate(R.layout.fragment_comment_bottom_sheet_dialog, container, false)    }    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        super.onViewCreated(view, savedInstanceState)        val request = arguments?.getString("id")        Log.d("REQUEST DATA", request.toString())    }}