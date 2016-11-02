(function () {


    function getCookie(name){
        var r = document.cookie.match("\\b" + name + "=([^;]*)\\b");
        return r ? r[1] : 'null';
    }
    function getList()
    {
        var i = 0;
        var ret = '' ; 
        var len = $("a.productImg").length; 
        for( i = 0 ; i < len; i ++  )
        {
            var r =  $("a.productImg")[i].href.match( /\d+/g )
            if( r ) 
                ret += r+"_";  
        }
        return ret ;
    }

    function getMobList()
    {
        var i = 0;
        var ret = '' ; 
        var anchors = $("section.plist" ).find( $('a')  )  ;
        for( i = 0 ; i < anchors.length; i ++  )
        {
            var r =  anchors[i].href.match( /sid=(\d+)/g );
            if( r ) 
            {
                ret += r[0].substring(4)+"_";  
            }
        }
        return ret ;
    }


    function pingList( list, name ) 
    {
        var img1 = new Image(1,1);
        var args = '';
        for( var i in params ) {
            if ( args != '' ) args += '&' ;
            if ( i == 'url' || i == 'sh' || i =='sw' || i=='cd' )
                args += i + '=' + encodeURIComponent(params[i]);
            else if ( i == 'referrer' )
                args += i + '=' + list;    
            else if ( i == 'account' ) 
                args += i + '=' + name;
            else
                args += i + '=';
        }
        //alert( args ) ;    
        img1.src = 'http://www.path-analytics.com/1.gif?' + args;
    }
    var params = {};
    //Document对象数据
    if(document) {
        params.domain = document.domain || ''; 
        params.url = document.URL || ''; 
        params.title = document.title || ''; 
        params.referrer = document.referrer || ''; 
	    params.viid = getCookie('_ViID') || '';
    }   
    if(window && window.screen) {
        params.sh = window.screen.height || 0;
        params.sw = window.screen.width || 0;
        params.cd = window.screen.colorDepth || 0;
    }
   
    //navigator对象数据
    if(navigator) {
        params.lang = navigator.language || ''; 
    }   
    //解析_maq配置
    if(_maq) {
        for(var i in _maq) {
            switch(_maq[i][0]) {
                case '_setAccount':
                    params.account = _maq[i][1];
                break;
                default:
                break;
            }   
        }   
    }   
    //拼接参数串
    var args = ''; 
    for(var i in params) {
        if(args != '') {
            args += '&';
        }   
        args += i + '=' + encodeURIComponent(params[i]);
    }   
    var img = new Image(1, 1); 
    img.src = 'http://www.path-analytics.com/1.gif?' + args;
    pro_ids = getList();
    if( pro_ids.length  >  0 )
    {
        var img1 = new Image(1,1);
        var args = '';
        for( var i in params ) {
            if ( args != '' ) args += '&' ;
            if ( i == 'url' || i == 'sh' || i =='sw' || i=='cd' )
                args += i + '=' + encodeURIComponent(params[i]);
            else if ( i == 'referrer' )
                args += i + '=' + pro_ids;    
            else if ( i == 'account' ) 
                args += i + '=' + 'productlist';
            else
                args += i + '=';
        }
        //alert( args ) ;    
        img1.src = 'http://www.path-analytics.com/1.gif?' + args;
    }

    mob_ids = getMobList() ;
    if( mob_ids.length > 0 ) 
        pingList( mob_ids, 'moblist' ) ;



//20150930  添加自定义模块逻辑，统计各种点击。
    $("a").on('click', function(event){
        var cur = $(this) ; 
        var tracks = '';
        while( cur.prop( 'tagName' ) != 'BODY' ) 
        {
            var trackid = cur.data( 'trackid' )   ;
            if( trackid ) tracks += trackid + '_' ;
            cur = cur.parent() ; 
        }
    	if ( tracks.length  > 0 ) 
		    pingList( tracks, 'shopin_track' ) ;
    });
})();
