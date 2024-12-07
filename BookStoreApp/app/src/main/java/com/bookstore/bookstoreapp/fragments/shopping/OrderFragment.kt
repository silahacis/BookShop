//package com.bookstore.bookstoreapp.fragments.shopping
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bookstore.bookstoreapp.R
//import com.bookstore.bookstoreapp.adapters.OrderAdapter
//import com.bookstore.bookstoreapp.data.Order
//import com.bookstore.bookstoreapp.databinding.FragmentOrderBinding
//import com.bookstore.bookstoreapp.viewmodel.OrderViewModel
//
//class OrderFragment : Fragment() {
//
//    private val orderViewModel: OrderViewModel by activityViewModels()
//
//    private lateinit var binding: FragmentOrderBinding
//    private lateinit var orderAdapter: OrderAdapter
//    private val orders = listOf<Order>() // Your list of orders
//
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentOrderBinding.inflate(inflater, container, false)
//        return binding.root
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_order, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val recyclerView = binding.rvAllOrders
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        orderAdapter = OrderAdapter(orders){selectedItem ->
//            val action = OrderFragmentDirections.actionOrderFragmentToBillingFragment()
//            findNavController().navigate(action)
//
//        }
//
//        /*orderViewModel.orderItems.observe(viewLifecycleOwner) { orders ->
//            if (orders.isNotEmpty()) {
//                binding.tvEmptyOrders.visibility = View.GONE
//                orderAdapter = OrderAdapter(orders.toMutableList(), onViewOrderDetails = { order ->
//                    // Implement order details functionality if needed
//                })
//                recyclerView.adapter = orderAdapter
//            } else {
//                binding.tvEmptyOrders.visibility = View.VISIBLE
//            }
//        }*/
//
//    }
//
//
//}