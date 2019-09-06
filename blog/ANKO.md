# ANKO







### 在Fragment中使用anko

>```java
>class HomeFragment : Fragment() {
>    override fun onCreateView(
>            inflater: LayoutInflater,
>            container: ViewGroup?,
>            savedInstanceState: Bundle?
>    ): View? {
>        return UI {
>            constraintLayout {
>                textView {
>                    text = "This Is Home Page"
>                    onClick {
>                        //todo something
>                    }
>                }.lparams()
>            }
>        }.view
>    }
>}
>```
>
>
>
>