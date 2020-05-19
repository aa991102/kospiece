$(function(){
            //서브메뉴 숨기기
            $('.sub-item-my').hide()
            $('.sub-item-virtual').hide()

            //"가상주식"메뉴에 마우스 올리면 서브메뉴 보이고/
            // 서브메뉴에서 마우스가 떠나면 서브메뉴 숨기기
            $('#virtual').mouseenter(function(){
                $('.list > li').eq(4).find('.sub-item-virtual').slideDown(100)
            })
            if($('.sub-item-virtual:visible')){
                $('.sub-list-virtual').mouseleave(function(){
                    $('.list > li').eq(4).find('.sub-item-virtual').slideUp(100)
                })
                $('.list-item').mouseleave(function(){
                    $('.list > li').eq(4).find('.sub-item-virtual').slideUp(100)
                })
            }

            //"마이페이지"메뉴에 마우스 올리면 서브메뉴 보이고/
            // 서브메뉴에서 마우스가 떠나면 서브메뉴 숨기기
            $('#my').hover(function(){
                $('.list > li').eq(5).find(".sub-item-my").slideDown(100)
            })
            if($('.sub-item-my:visible')){
                $('.sub-list-my').mouseleave(function(){
                    $('.list > li').eq(5).find('.sub-item-my').slideUp(100)
                })
                $('.list-item').mouseleave(function(){
                    $('.list > li').eq(5).find('.sub-item-my').slideUp(100)
                })
            }
        })