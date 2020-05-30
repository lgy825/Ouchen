// JavaScript Document
$(function () {
    // home nav
    $('.aside-list').on('click', '.aside-title', function () {
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).children('.child-list').stop().slideToggle(200);
        $(this).siblings().children('ul').stop().slideUp(200);
        $(this).toggleClass('sub-blue').siblings().removeClass('sub-blue');
    });
    $('.child-menu').on('click', function (event) {
        event.stopPropagation();
        $('.child-menu').removeClass('cur');
        $(this).addClass('cur');
    });

});

/**
 * init easy-ui height
 * @param subHeight redundant height
 */
function initHeight(subHeight) {
    var screenHeight = $(window).height(),
        subH = subHeight;
    $('.data-form').height(screenHeight - subH);
}

/**
 * clone Element
 * @param trigger 触发事件
 * @param parentWrap 被拷贝元素的父类
 * @param clonedChild 被拷贝元素
 * @param deleteDom 删除拷贝的节点
 */
function cloneFn(trigger, parentWrap, clonedChild, deleteDom) {
    trigger.on('click', function () {
        parentWrap.append(clonedChild.clone(true).removeClass('none'));
    });
    deleteDom.on('click', function () {
        $(this).parent().parent().remove();
    });
}