/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 $(document).ready(function(){
            $('.cur-review ul').slideDown(500);
            
            $('.review .review-banner').click(function(){
                if(!$(this).parent().hasClass('cur-review')){
                    $('.cur-review ul').slideUp(500).parent().removeClass('cur-review');
                    $(this).parent().addClass('cur-review').find('ul').slideDown(500);
                }
            });
        });

