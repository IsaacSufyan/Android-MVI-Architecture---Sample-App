package com.isaacsufyan.mvi.ui.features.employees

//@InternalCoroutinesApi
//class EmployeeListFragment :
//    BaseFragment<EmployeeListContract.State, EmployeeListContract.Event,
//        EmployeeListContract.Effect, EmployeeListViewModel, EmployeeListFragmentBinding>(
//        R.layout.employee_list_fragment,
//        EmployeeListViewModel::class
//    ) {
//
//    private lateinit var adapter: EmployeeListAdapter
//    private lateinit var layoutManager: LinearLayoutManager
//
//    override fun setupViews(view: View) {
//        super.setupViews(view)
//        onBackEvent = { requireActivity().moveTaskToBack(true) }
//        adapter = EmployeeListAdapter {
////            navigateTo(EmployeeListFragmentDirections.toEmployeeDetailsFragment())
//        }
//        layoutManager = LinearLayoutManager(requireContext())
//        with(binding) {
//            employeesRecyclerView.apply {
//                layoutManager = this@EmployeeListFragment.layoutManager
//                adapter = this@EmployeeListFragment.adapter
//                addItemDecoration(
//                    DividerItemDecoration(
//                        employeesRecyclerView.context,
//                        this@EmployeeListFragment.layoutManager.orientation
//                    )
//                )
//            }
//            fab.setOnClickListener {
//                viewModel.onAddEmployeeButtonClicked()
//            }
//        }
//        with(viewModel) {
//            adapter.loadStateFlow
//                .map { it.refresh }
//                .distinctUntilChanged()
//                .onEach {
//                    when (it) {
//                        is LoadState.Loading -> setLoadingState()
//                        is LoadState.NotLoading -> {
//                            if (adapter.itemCount == 0) {
//                                setEmptyState()
//                            } else {
//                                setSuccessState()
//                            }
//                        }
//                        is LoadState.Error -> setErrorState()
//                    }
//                }
//                .observeInLifecycle(viewLifecycleOwner)
//
//            employees
//                .onEach { adapter.submitData(it) }
//                .observeInLifecycle(viewLifecycleOwner)
//        }
//    }
//
//    override fun handleState(it: EmployeeListContract.State) {
//        if (it is EmployeeListContract.State.Empty) {
//            view?.findViewById<TextView>(R.id.empty_text)?.visibility = View.VISIBLE
//        } else {
//            view?.findViewById<TextView>(R.id.empty_text)?.visibility = View.GONE
//        }
//    }
//}
