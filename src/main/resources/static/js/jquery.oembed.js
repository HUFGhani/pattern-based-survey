(function($) {
    $.fn.oembed = function(url, options, callback) {

        options = $.extend(true, $.fn.oembed.defaults, options);

        return this.each(function() {

            var container = $(this),
				resourceURL = (url != null) ? url : container.attr("href"),
				provider;

            if (!callback) callback = function(container, oembed) {			
				 $.fn.oembed.insertCode(container, options.embedMethod, oembed);
            };

            if (resourceURL != null) {
                provider = getOEmbedProvider(resourceURL);

                if (provider != null) {						
					provider.params = getNormalizedParams(options[provider.name]) || {};
                    provider.maxWidth = options.maxWidth;
                    provider.maxHeight = options.maxHeight;										
                    provider.embedCode(container, resourceURL, callback);
                    return;
                }
            }

            callback(container, null);
        });
    };

    // Plugin defaults
    $.fn.oembed.defaults = {
        maxWidth: null,
        maxHeight: null,
		embedMethod: "replace" // "auto", "append", "fill"
    };
	
	$.fn.oembed.insertCode = function(container, embedMethod, oembed) {
		if (oembed == null)
			return;
		switch(embedMethod)
		{
			case "auto":				
                if (container.attr("href") != null) {
					$.fn.oembed.insertCode(container, "append", oembed);
				}
				else {
					$.fn.oembed.insertCode(container, "replace", oembed);
				};
				break;
			case "replace":	
				container.replaceWith(oembed.code);
				break;
			case "fill":
				container.html(oembed.code);
				break;
			case "append":
                var oembedContainer = container.next();
				if (oembedContainer == null || !oembedContainer.hasClass("oembed-container")) {
					oembedContainer = container
						.after('<div class="oembed-container"></div>')
						.next(".oembed-container");
					if (oembed != null && oembed.provider_name != null)
					    oembedContainer.toggleClass("oembed-container-" + oembed.provider_name);		
				}
				oembedContainer.html(oembed.code);				
				break;			
		}
	};

    $.fn.oembed.getPhotoCode = function(url, data) {
	    var alt = data.title ? data.title : '';
        alt += data.author_name ? ' - ' + data.author_name : '';
        alt += data.provider_name ? ' - ' +data.provider_name : '';
        var code = '<div><a href="' + url + '" target="_blank"><img src="' + data.url + '" alt="' + alt + '"/></a></div>';
        if (data.html)
            code += "<div>" + data.html + "</div>";
        return code;
    };

    $.fn.oembed.getVideoCode = function(url, data) {
        var code = data.html;
        return code;
    };

    $.fn.oembed.getRichCode = function(url, data) {
        var code = data.html;
        return code;
    };

    $.fn.oembed.getGenericCode = function(url, data) {
        var title = (data.title != null) ? data.title : url,
			code = '<a href="' + url + '">' + title + '</a>';
        if (data.html)
            code += "<div>" + data.html + "</div>";
        return code;
    };

    $.fn.oembed.isAvailable = function(url) {
        var provider = getOEmbedProvider(url);
        return (provider != null);
    };

    /* Private Methods */
    function getOEmbedProvider(url) {
        for (var i = 0; i < providers.length; i++) {
            if (providers[i].matches(url))
                return providers[i];
        }
        return null;
    }
	
	function getNormalizedParams(params) {
		if (params == null)
			return null;
		var normalizedParams = {};
		for (var key in params) {
			if (key != null)
				normalizedParams[key.toLowerCase()] = params[key];
		}
		return normalizedParams;
	}

    	var providers = [
			new OEmbedProvider("wikipedia", "http:\/\/wikipedia\.org\/wiki\/.*", "http://oohembed.com/oohembed/"),
			new OEmbedProvider("youtube", "http:\/\/.*youtube\.com\/watch.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("youtube", "http:\/\/.*\.youtube\.com\/v\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("youtube", "http:\/\/youtu\.be\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("youtube", "http:\/\/.*\.youtube\.com\/user\/.*\#.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("youtube", "http:\/\/.*\.youtube\.com\/.*\#.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("justintv", "http:\/\/.*justin\.tv\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("justintv", "http:\/\/.*justin\.tv\/.*\/b\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ustream", "http:\/\/www\.ustream\.tv\/recorded\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ustream", "http:\/\/www\.ustream\.tv\/channel\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("qik", "http:\/\/qik\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("qik", "http:\/\/qik\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("revision3", "http:\/\/.*revision3\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dailymotion", "http:\/\/.*\.dailymotion\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dailymotion", "http:\/\/.*\.dailymotion\.com\/.*\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("collegehumor", "http:\/\/www\.collegehumor\.com\/video:.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("twitvid", "http:\/\/.*twitvid\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("break", "http:\/\/www\.break\.com\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("myspacevideos", "http:\/\/vids\.myspace\.com\/index\.cfm\?fuseaction=vids\.individual&videoid.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("myspacevideos", "http:\/\/www\.myspace\.com\/index\.cfm\?fuseaction=.*&videoid.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("metacafe", "http:\/\/www\.metacafe\.com\/watch\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("bliptv", "http:\/\/blip\.tv\/file\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("bliptv", "http:\/\/.*\.blip\.tv\/file\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("googlevideo", "http:\/\/video\.google\.com\/videoplay\?.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("revver", "http:\/\/.*revver\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yahoovideo", "http:\/\/video\.yahoo\.com\/watch\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yahoovideo", "http:\/\/video\.yahoo\.com\/network\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("viddler", "http:\/\/.*viddler\.com\/explore\/.*\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("liveleak", "http:\/\/liveleak\.com\/view\?.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("liveleak", "http:\/\/www\.liveleak\.com\/view\?.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("animoto", "http:\/\/animoto\.com\/play\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dotsub", "http:\/\/dotsub\.com\/view\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("overstream", "http:\/\/www\.overstream\.net\/view\.php\?oid=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("livestream", "http:\/\/www\.livestream\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("worldstarhiphop", "http:\/\/www\.worldstarhiphop\.com\/videos\/video.*\.php\?v=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("worldstarhiphop", "http:\/\/worldstarhiphop\.com\/videos\/video.*\.php\?v=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("teachertube", "http:\/\/teachertube\.com\/viewVideo\.php.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("teachertube", "http:\/\/teachertube\.com\/viewVideo\.php.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("bambuser", "http:\/\/bambuser\.com\/v\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("bambuser", "http:\/\/bambuser\.com\/channel\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("bambuser", "http:\/\/bambuser\.com\/channel\/.*\/broadcast\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("whitehouse", "http:\/\/www\.whitehouse\.gov\/photos-and-video\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("whitehouse", "http:\/\/www\.whitehouse\.gov\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("whitehouse", "http:\/\/wh\.gov\/photos-and-video\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("whitehouse", "http:\/\/wh\.gov\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("hulu", "http:\/\/www\.hulu\.com\/watch.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("hulu", "http:\/\/www\.hulu\.com\/w\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("hulu", "http:\/\/hulu\.com\/watch.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("hulu", "http:\/\/hulu\.com\/w\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("movieclips", "http:\/\/movieclips\.com\/watch\/.*\/.*\/", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("movieclips", "http:\/\/movieclips\.com\/watch\/.*\/.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("crackle", "http:\/\/.*crackle\.com\/c\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("fancast", "http:\/\/www\.fancast\.com\/.*\/videos", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("funnyordie", "http:\/\/www\.funnyordie\.com\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("vimeo", "http:\/\/www\.vimeo\.com\/groups\/.*\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("vimeo", "http:\/\/www\.vimeo\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("vimeo", "http:\/\/vimeo\.com\/groups\/.*\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("vimeo", "http:\/\/vimeo\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ted", "http:\/\/www\.ted\.com\/talks\/.*\.html.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ted", "http:\/\/www\.ted\.com\/talks\/lang\/.*\/.*\.html.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ted", "http:\/\/www\.ted\.com\/index\.php\/talks\/.*\.html.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("ted", "http:\/\/www\.ted\.com\/index\.php\/talks\/lang\/.*\/.*\.html.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("omnisio", "http:\/\/.*omnisio\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("nfb", "http:\/\/.*nfb\.ca\/film\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("thedailyshow", "http:\/\/www\.thedailyshow\.com\/watch\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("thedailyshow", "http:\/\/www\.thedailyshow\.com\/full-episodes\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("thedailyshow", "http:\/\/www\.thedailyshow\.com\/collection\/.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yahoomovies", "http:\/\/movies\.yahoo\.com\/movie\/.*\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yahoomovies", "http:\/\/movies\.yahoo\.com\/movie\/.*\/info", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yahoomovies", "http:\/\/movies\.yahoo\.com\/movie\/.*\/trailer", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("colbertnation", "http:\/\/www\.colbertnation\.com\/the-colbert-report-collections\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("colbertnation", "http:\/\/www\.colbertnation\.com\/full-episodes\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("colbertnation", "http:\/\/www\.colbertnation\.com\/the-colbert-report-videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("comedycentral", "http:\/\/www\.comedycentral\.com\/videos\/index\.jhtml\?.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("theonion", "http:\/\/www\.theonion\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("theonion", "http:\/\/theonion\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("wordpresstv", "http:\/\/wordpress\.tv\/.*\/.*\/.*\/.*\/", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("traileraddict", "http:\/\/www\.traileraddict\.com\/trailer\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("traileraddict", "http:\/\/www\.traileraddict\.com\/clip\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("traileraddict", "http:\/\/www\.traileraddict\.com\/poster\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("escapistmagazine", "http:\/\/www\.escapistmagazine\.com\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("trailerspy", "http:\/\/www\.trailerspy\.com\/trailer\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("trailerspy", "http:\/\/www\.trailerspy\.com\/trailer\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("trailerspy", "http:\/\/www\.trailerspy\.com\/view_video\.php.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("atom", "http:\/\/www\.atom\.com\/.*\/.*\/", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("foratv", "http:\/\/fora\.tv\/.*\/.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("spike", "http:\/\/www\.spike\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("gametrailers", "http:\/\/www\.gametrailers\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("gametrailers", "http:\/\/gametrailers\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("godtube", "http:\/\/www\.godtube\.com\/featured\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("tangle", "http:\/\/www\.tangle\.com\/view_video.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("twitter", "http:\/\/twitter\.com\/.*\/status\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("twitter", "http:\/\/twitter\.com\/.*\/statuses\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("slideshare", "http:\/\/www\.slideshare\.net\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("scribd", "http:\/\/.*\.scribd\.com\/doc\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("screenr", "http:\/\/screenr\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("5min", "http:\/\/www\.5min\.com\/Video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("howcast", "http:\/\/www\.howcast\.com\/videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("screencast", "http:\/\/www\.screencast\.com\/.*\/media\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("screencast", "http:\/\/screencast\.com\/.*\/media\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("screencast", "http:\/\/www\.screencast\.com\/t\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("screencast", "http:\/\/screencast\.com\/t\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("issuu", "http:\/\/issuu\.com\/.*\/docs\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("kickstarter", "http:\/\/www\.kickstarter\.com\/projects\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("scrapblog", "http:\/\/www\.scrapblog\.com\/viewer\/viewer\.aspx.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("myopera", "http:\/\/my\.opera\.com\/.*\/albums\/show\.dml\?id=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("myopera", "http:\/\/my\.opera\.com\/.*\/albums\/showpic\.dml\?album=.*&picture=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("tumblr", "http:\/\/tumblr\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("tumblr", "http:\/\/.*\.tumblr\.com\/post\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("statusnet", "http:\/\/.*\.status\.net\/notice\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("identica", "http:\/\/identi\.ca\/notice\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("shitmydadsays", "http:\/\/shitmydadsays\.com\/notice\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("yfrog", "http:\/\/.*yfrog\..*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("tweetphoto", "http:\/\/tweetphoto\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("flickr", "http:\/\/www\.flickr\.com\/photos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("twitpic", "http:\/\/.*twitpic\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("imgur", "http:\/\/.*imgur\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("posterous", "http:\/\/.*\.posterous\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("posterous", "http:\/\/post\.ly\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("twitgoo", "http:\/\/twitgoo\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("photobucket", "http:\/\/i.*\.photobucket\.com\/albums\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("photobucket", "http:\/\/gi.*\.photobucket\.com\/groups\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("phodroid", "http:\/\/phodroid\.com\/.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("mobypicture", "http:\/\/www\.mobypicture\.com\/user\/.*\/view\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("mobypicture", "http:\/\/moby\.to\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("xkcd", "http:\/\/xkcd\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("xkcd", "http:\/\/www\.xkcd\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("asofterworld", "http:\/\/www\.asofterworld\.com\/index\.php\?id=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dinosaurcomics", "http:\/\/www\.qwantz\.com\/index\.php\?comic=.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("23hq", "http:\/\/23hq\.com\/.*\/photo\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("23hq", "http:\/\/www\.23hq\.com\/.*\/photo\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dribbble", "http:\/\/.*dribbble\.com\/shots\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dribbble", "http:\/\/drbl\.in\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("smugmug", "http:\/\/.*\.smugmug\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("smugmug", "http:\/\/.*\.smugmug\.com\/.*\#.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("emberapp", "http:\/\/emberapp\.com\/.*\/images\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("emberapp", "http:\/\/emberapp\.com\/.*\/images\/.*\/sizes\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("emberapp", "http:\/\/emberapp\.com\/.*\/collections\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("emberapp", "http:\/\/emberapp\.com\/.*\/categories\/.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("emberapp", "http:\/\/embr\.it\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("picasa", "http:\/\/picasaweb\.google\.com.*\/.*\/.*\#.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("picasa", "http:\/\/picasaweb\.google\.com.*\/lh\/photo\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("picasa", "http:\/\/picasaweb\.google\.com.*\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("dailybooth", "http:\/\/dailybooth\.com\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("brizzly", "http:\/\/brizzly\.com\/pic\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("brizzly", "http:\/\/pics\.brizzly\.com\/.*\.jpg", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("imgly", "http:\/\/img\.ly\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/gp\/product\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/.*\/dp\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/dp\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/o\/ASIN\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/gp\/offer-listing\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/.*\/ASIN\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/.*amazon\..*\/gp\/product\/images\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/www\.amzn\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("amazon", "http:\/\/amzn\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("shopstyle", "http:\/\/www\.shopstyle\.com\/browse.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("shopstyle", "http:\/\/www\.shopstyle\.com\/action\/apiVisitRetailer.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("shopstyle", "http:\/\/www\.shopstyle\.com\/action\/viewLook.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("soundcloud", "http:\/\/soundcloud\.com\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("soundcloud", "http:\/\/soundcloud\.com\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("soundcloud", "http:\/\/soundcloud\.com\/.*\/sets\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("soundcloud", "http:\/\/soundcloud\.com\/groups\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("lastfm", "http:\/\/www\.last\.fm\/music\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("lastfm", "http:\/\/www\.last\.fm\/music\/+videos\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("lastfm", "http:\/\/www\.last\.fm\/music\/+images\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("lastfm", "http:\/\/www\.last\.fm\/music\/.*\/_\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("lastfm", "http:\/\/www\.last\.fm\/music\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("mixcloud", "http:\/\/www\.mixcloud\.com\/.*\/.*\/", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("espn", "http:\/\/espn\.go\.com\/video\/clip.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("espn", "http:\/\/espn\.go\.com\/.*\/story.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("cnbc", "http:\/\/cnbc\.com\/id\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("cbsnews", "http:\/\/cbsnews\.com\/video\/watch\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("cnn", "http:\/\/www\.cnn\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("cnnedition", "http:\/\/edition\.cnn\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("cnnmoney", "http:\/\/money\.cnn\.com\/video\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("msnbc", "http:\/\/today\.msnbc\.msn\.com\/id\/.*\/vp\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("msnbc", "http:\/\/www\.msnbc\.msn\.com\/id\/.*\/vp\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("msnbc", "http:\/\/www\.msnbc\.msn\.com\/id\/.*\/ns\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("msnbc", "http:\/\/today\.msnbc\.msn\.com\/id\/.*\/ns\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("foxsports", "http:\/\/multimedia\.foxsports\.com\/m\/video\/.*\/.*", "http://api.embed.ly/v1/api/oembed"),
			new OEmbedProvider("foxsports", "http:\/\/msn\.foxsports\.com\/video.*", "http://api.embed.ly/v1/api/oembed")
	];

    function OEmbedProvider(name, urlPattern, oEmbedUrl, callbackparameter) {
        this.name = name;
        this.urlPattern = urlPattern;
        this.oEmbedUrl = (oEmbedUrl != null) ? oEmbedUrl : "http://oohembed.com/oohembed/";
        this.callbackparameter = (callbackparameter != null) ? callbackparameter : "callback";
        this.maxWidth = 500;
        this.maxHeight = 400;

        this.matches = function(externalUrl) {
			return externalUrl.match(this.urlPattern) != null;
		};

        this.getRequestUrl = function(externalUrl) {

            var url = this.oEmbedUrl;

            if (url.indexOf("?") <= 0)
                url = url + "?";
			else
				url = url + "&";

			var qs = "";
			
			if (this.maxWidth != null && this.params["maxwidth"] == null)
				this.params["maxwidth"] = this.maxWidth;				
				
			if (this.maxHeight != null && this.params["maxheight"] == null)
				this.params["maxheight"] = this.maxHeight;

			for (var i in this.params) {
                // We don't want them to jack everything up by changing the callback parameter
                if (i == this.callbackparameter)
                  continue;
                
				// allows the options to be set to null, don't send null values to the server as parameters
                if (this.params[i] != null)
                	qs += "&" + escape(i) + "=" + this.params[i];
            }			
            
				
			url += "format=json&url=" + escape(externalUrl) + 			
					qs + 
					"&" + this.callbackparameter + "=?";
					
            return url;
        };

        this.embedCode = function(container, externalUrl, callback) {

            var request = this.getRequestUrl(externalUrl);

            $.getJSON(request, function(data) {

                var oembed = $.extend(data);

                var code, type = data.type;

                switch (type) {
                    case "photo":
                        oembed.code = $.fn.oembed.getPhotoCode(externalUrl, data);
                        break;
                    case "video":
                        oembed.code = $.fn.oembed.getVideoCode(externalUrl, data);
                        break;
                    case "rich":
                        oembed.code = $.fn.oembed.getRichCode(externalUrl, data);
                        break;
                    default:
                        oembed.code = $.fn.oembed.getGenericCode(externalUrl, data);
                        break;
                }

                callback(container, oembed);
            });
        };
    }
})(jQuery);